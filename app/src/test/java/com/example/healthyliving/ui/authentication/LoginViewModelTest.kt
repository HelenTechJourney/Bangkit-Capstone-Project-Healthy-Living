package com.example.healthyliving.ui.authentication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.healthyliving.remote.response.LoginResponse
import com.example.healthyliving.utils.DataDummy
import com.example.healthyliving.utils.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        viewModel = Mockito.mock(LoginViewModel::class.java)
    }

    @Test
    fun `when login message should return the right data and not null`() {
        val expectedLoginMessage = MutableLiveData<String>()
        expectedLoginMessage.value = "Login Successfully"

        Mockito.`when`(viewModel.message).thenReturn(expectedLoginMessage)
        val actualMessage = viewModel.message.getOrAwaitValue()
        Mockito.verify(viewModel).message

        assertNotNull(actualMessage)
        assertEquals(expectedLoginMessage.value, actualMessage)
    }

    @Test
    fun `when loading state should return the right data and not null`() {
        val expectedLoadingData = MutableLiveData<Boolean>()
        expectedLoadingData.value = true

        Mockito.`when`(viewModel.isLoading).thenReturn(expectedLoadingData)

        val actualLoading = viewModel.isLoading.getOrAwaitValue()

        Mockito.verify(viewModel).isLoading
        assertNotNull(actualLoading)
        assertEquals(expectedLoadingData.value, actualLoading)
    }

    @Test
    fun `when login should return the right login user data and not null`() {
        val dummyResponselogin = DataDummy.generateDummyResponseLogin()

        val expectedLogin = MutableLiveData<LoginResponse>()
        expectedLogin.value = dummyResponselogin

        Mockito.`when`(viewModel.userLogin).thenReturn(expectedLogin)

        val actualLoginResponse = viewModel.userLogin.getOrAwaitValue()

        Mockito.verify(viewModel).userLogin
        assertNotNull(actualLoginResponse)
        assertEquals(expectedLogin.value, actualLoginResponse)
    }

    @Test
    fun `verify getLoginResponse function is working`() {
        val dummyRequestLogin = DataDummy.generateDummyRequestLogin()
        val dummyResponseLogin = DataDummy.generateDummyResponseLogin()

        val expectedResponseLogin = MutableLiveData<LoginResponse>()
        expectedResponseLogin.value = dummyResponseLogin

        viewModel.getLoginResponse(dummyRequestLogin)

        Mockito.verify(viewModel).getLoginResponse(dummyRequestLogin)

        Mockito.`when`(viewModel.userLogin).thenReturn(expectedResponseLogin)

        val actualData = viewModel.userLogin.getOrAwaitValue()

        Mockito.verify(viewModel).userLogin
        assertNotNull(expectedResponseLogin)
        assertEquals(expectedResponseLogin.value, actualData)
    }
}