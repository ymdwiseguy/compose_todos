package com.ymdwiseguy.todos.viewmodel

import com.ymdwiseguy.todos.TodosTestDataStore
import com.ymdwiseguy.todos.domain.Todo
import com.ymdwiseguy.todos.repo.TodosRepository
import com.ymdwiseguy.todos.shouldBe
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class TodosViewModelTest {

    private lateinit var todosViewModel: TodosViewModel
    private lateinit var todosRepository: TodosRepository
    private lateinit var todosDataStore: TodosTestDataStore

    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        todosDataStore = TodosTestDataStore()
        todosRepository = TodosRepository(mockk(), todosDataStore)
        todosViewModel = TodosViewModel(todosRepository)
    }

    @AfterEach
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `add some to do's`() {
        runTest {
            with(todosViewModel) {
                viewData.value shouldBe emptyList<Todo>()

                val todo1 = Todo(name = "first to do")
                val todo2 = Todo(name = "second to do")
                val todo3 = Todo(name = "third to do")
                val todo4 = Todo(name = "fourth to do")

                addTodo(todo1)
                addTodo(todo2)
                addTodo(todo3)
                addTodo(todo4)

                viewData.value shouldBe listOf(
                    todo1.copy(sortIndex = 1),
                    todo2.copy(sortIndex = 2),
                    todo3.copy(sortIndex = 3),
                    todo4.copy(sortIndex = 4),
                )
            }
        }
    }

    @Test
    fun `delete a todo`() {
        runTest {
            with(todosViewModel) {
                val todo1 = Todo(name = "first to do")
                val todo2 = Todo(name = "second to do")
                val todo3 = Todo(name = "third to do")
                val todo4 = Todo(name = "fourth to do")

                addTodo(todo1)
                addTodo(todo2)
                addTodo(todo3)
                addTodo(todo4)

                removeTodo(todo2)

                viewData.value shouldBe listOf(
                    todo1.copy(sortIndex = 1),
                    todo3.copy(sortIndex = 3),
                    todo4.copy(sortIndex = 4),
                )
            }
        }
    }

    @Test
    fun `update a todo`() {
        runTest {
            with(todosViewModel) {
                val todo1 = Todo(name = "first to do")

                addTodo(todo1)

                updateTodo(todo1.copy(name = "updated first to do"))

                viewData.value.first().name shouldBe "updated first to do"
            }
        }
    }

    @Test
    fun `move a to do`() {
        runTest {
            with(todosViewModel) {
                val todo1 = Todo(name = "first to do")
                val todo2 = Todo(name = "second to do")
                val todo3 = Todo(name = "third to do")
                val todo4 = Todo(name = "fourth to do")

                addTodo(todo1)
                addTodo(todo2)
                addTodo(todo3)
                addTodo(todo4)

                moveTodo(todo = todo4, to = 2)

                viewData.value shouldBe listOf(
                    todo1.copy(sortIndex = 1),
                    todo4.copy(sortIndex = 4),
                    todo2.copy(sortIndex = 2),
                    todo3.copy(sortIndex = 3),
                )
            }
        }
    }



}