package br.edu.up.rgm.approom33664561.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.rgm.approom33664561.data.Item
import br.edu.up.rgm.approom33664561.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class HomeViewModel(itemsRepository: ItemsRepository) : ViewModel() {

  //retornar estados dos itens ui para apresentar na tela
    val homeUiState: StateFlow<HomeUiState> =
        itemsRepository.getAllItemsStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

// lista os itens
data class HomeUiState(val itemList: List<Item> = listOf())
