package com.example.healthyliving.ui.authentication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
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
class SignupViewModelTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: SignUpViewModel

    @Before
    fun setUp() {
        viewModel = Mockito.mock(SignUpViewModel::class.java)
    }

    @Test
    fun `when signup message should return right data and not null`() {
        val expectedSignup = MutableLiveData<String>()
        expectedSignup.value = "User Created"

        Mockito.`when`(viewModel.message).thenReturn(expectedSignup)
        val actualMessage = viewModel.message.getOrAwaitValue()
        Mockito.verify(viewModel).message

        assertNotNull(actualMessage)
        assertEquals(expectedSignup.value, actualMessage)
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
    fun `verify getSignupResponse function is working`() {
        val dummyRequestSignup = DataDummy.generateDummyRequestSignUp()
        val expectedRegisterMessage = MutableLiveData<String>()
        expectedRegisterMessage.value = "User Created"

        viewModel.getSignupResponse(dummyRequestSignup)

        Mockito.verify(viewModel).getSignupResponse(dummyRequestSignup)

        Mockito.`when`(viewModel.message).thenReturn(expectedRegisterMessage)

        val actualData = viewModel.message.getOrAwaitValue {  }

        Mockito.verify(viewModel).message
        assertNotNull(actualData)
        assertEquals(expectedRegisterMessage.value, actualData)
    }
}