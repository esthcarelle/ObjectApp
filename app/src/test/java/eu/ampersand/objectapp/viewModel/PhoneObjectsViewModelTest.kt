package eu.ampersand.objectapp.viewModel

import eu.ampersand.objectapp.model.Data
import eu.ampersand.objectapp.model.PhoneObjectItem
import eu.ampersand.objectapp.repository.PhoneObjectsRepository
import eu.ampersand.objectapp.ui.transform.PhoneObjectsViewModel
import eu.ampersand.objectapp.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class PhoneObjectsViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: PhoneObjectsViewModel
    private val repository: PhoneObjectsRepository = mockk()

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @BeforeEach
    fun setUp() {

        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = PhoneObjectsViewModel(repository)
    }

    @Test
    fun testGetApprovals() = runTest {
        // Create an instance of the Data class
        val phoneData = Data(
            cpuModel = "A14 Bionic",
            capacityC = "256GB",
            caseSize = "6.1 inches",
            colorC = "Pacific Blue",
            descriptionD = "The latest model with incredible speed and performance.",
            generationG = "5th Generation",
            hardDiskSize = "512GB",
            priceP = "1200",
            screenSize = 6.1,
            strapColour = "Black",
            capacity = "256GB",
            capacityGB = 256,
            color = "Blue",
            generation = "12th Generation",
            price = 1099.99,
            year = 2024
        )

        // Create an instance of the PhoneObjectItem class
        val phoneObjectItem = PhoneObjectItem(
            data = phoneData,
            id = "1",
            name = "iPhone 12 Pro Max"
        )

        // Create another instance of the Data class
        val anotherPhoneData = Data(
            cpuModel = "Snapdragon 888",
            capacityC = "128GB",
            caseSize = "6.8 inches",
            colorC = "Phantom Black",
            descriptionD = "Flagship model with excellent battery life and display.",
            generationG = "4th Generation",
            hardDiskSize = "256GB",
            priceP = "999",
            screenSize = 6.8,
            strapColour = "Silver",
            capacity = "128GB",
            capacityGB = 128,
            color = "Black",
            generation = "4th Generation",
            price = 999.99,
            year = 2023
        )

        // Create another instance of the PhoneObjectItem class
        val anotherPhoneObjectItem = PhoneObjectItem(
            data = anotherPhoneData,
            id = "2",
            name = "Samsung Galaxy S21 Ultra"
        )


        val approvalsList = listOf(
            phoneObjectItem,
            anotherPhoneObjectItem
        )
        coEvery { repository.getPhoneObjects() } returns flow {
            emit(NetworkResult.Success(approvalsList))
        }

        viewModel.getPhoneObjects()
        assertEquals(false, viewModel.isLoading.value)
        assertTrue(viewModel.phoneObject.value is NetworkResult.Success)
        assertEquals(approvalsList, (viewModel.phoneObject.value as NetworkResult.Success).data)
    }

    @Test
    fun testGetApprovalsFailure() = runTest {
        coEvery { repository.getPhoneObjects() } returns flow {
            emit(NetworkResult.Failure("Error fetching objects"))
        }

        viewModel.getPhoneObjects()

        assertEquals(false, viewModel.isLoading.value)
        assertTrue(viewModel.phoneObject.value is NetworkResult.Failure)
        assertEquals(
            "Error fetching objects",
            (viewModel.phoneObject.value as NetworkResult.Failure).errorMessage
        )
    }
}