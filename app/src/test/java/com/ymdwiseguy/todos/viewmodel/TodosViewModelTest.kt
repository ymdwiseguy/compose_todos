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
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class TodosViewModelTest {

    private lateinit var viewModel: TodosViewModel
    private lateinit var todosRepository: TodosRepository
    private lateinit var todosDataStore: TodosTestDataStore

    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        todosDataStore = TodosTestDataStore()
        todosRepository = TodosRepository(mockk(), todosDataStore)
        viewModel = TodosViewModel(todosRepository)
    }

    @AfterEach
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `add first todo`() {
        viewModel.viewData.value shouldBe emptyList<Todo>()
    }
}