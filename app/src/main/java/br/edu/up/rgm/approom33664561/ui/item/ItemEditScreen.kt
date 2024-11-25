package br.edu.up.rgm.approom33664561.ui.item

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.up.rgm.approom33664561.InventoryTopAppBar
import br.edu.up.rgm.approom33664561.R
import br.edu.up.rgm.approom33664561.ui.AppViewModelProvider
import br.edu.up.rgm.approom33664561.ui.navigation.NavigationDestination
import br.edu.up.rgm.approom33664561.ui.theme.InventoryTheme
import kotlinx.coroutines.launch

object ItemEditDestination : NavigationDestination {
    override val route = "edit_item"
    override val titleRes = R.string.edit_item_title
    const val argumentKey = "itemId"
    val routeWithArguments = "$route/{$argumentKey}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    onBackNavigate: () -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItemEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(ItemEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        ItemEntryBody(
            itemUiState = viewModel.itemUiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                scope.launch {
                    viewModel.updateItem()
                    onBackNavigate()
                }
            },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                )
                .verticalScroll(rememberScrollState())
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemEditScreen() {
    InventoryTheme {
        ItemEditScreen(onBackNavigate = { /*No action*/ }, navigateUp = { /*No action*/ })
    }
}
