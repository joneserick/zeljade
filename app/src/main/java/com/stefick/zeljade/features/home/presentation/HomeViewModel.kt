package com.stefick.zeljade.features.home.presentation

import android.widget.ArrayAdapter
import androidx.lifecycle.*
import com.stefick.zeljade.core.models.*
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.repository.ICompendiumRepository
import com.stefick.zeljade.core.repository.Repository
import com.stefick.zeljade.features.home.models.CategoryCardItem
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ICompendiumRepository
) : ViewModel(), LifecycleObserver {

    private val _categories: MutableLiveData<CompendiumResponse> by lazy {
        MutableLiveData<CompendiumResponse>()
    }

    private val _entry: MutableLiveData<EntryResponse> by lazy {
        MutableLiveData<EntryResponse>()
    }

    private val _error: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val categories: LiveData<CompendiumResponse> = _categories
    val entry: LiveData<EntryResponse> = _entry
    val error: LiveData<String> = _error

    fun requestAllData() {
        viewModelScope.launch {
            repository.requestAllData()
                .collect { result ->
                    when (result) {
                        is Repository.Result.Success<*> -> _categories.value =
                            (result.result as CompendiumResponse)
                        is Repository.Result.Failed<*> -> _error.value =
                            ((result.result as ErrorResponse).message)
                        is Repository.Result.Unknown -> {
                            _error.value = "WIP: should improve error response: ${result.url}"
                        }
                        else -> _error.value = "WIP: default error value"
                    }
                }
        }
    }


    fun requestEntry(entryId: Int) {
        viewModelScope.launch {
            repository.requestEntryData(entryId)
                .collect { result ->
                    when (result) {
                        is Repository.Result.Success<*> -> _entry.postValue(
                            (result.result as EntryResponse)
                        )
                        is Repository.Result.Failed<*> -> _error.value =
                            ((result.result as ErrorResponse).message)
                        is Repository.Result.Unknown -> {
                            _error.value = "WIP: should improve error response: ${result.url}"
                        }
                        else -> _error.value = "WIP: default error value"
                    }
                }
        }
    }

    fun getSelectedSimpleCategoryItems(category: String?): ArrayList<CategoryResponse>? {
        return when (category) {
            CategoryEnum.EQUIPMENT.category -> categories.value?.data?.equipment
            CategoryEnum.MATERIALS.category -> categories.value?.data?.materials
            CategoryEnum.TREASURE.category -> categories.value?.data?.treasure
            CategoryEnum.MONSTERS.category -> categories.value?.data?.monsters
            else -> null
        }
    }

    fun getEachCategory(categories: CategoriesResponse?): List<CategoryCardItem> {
        return arrayListOf<CategoryCardItem>().apply {
            categories.let { model ->
                val creatures = model?.creatures?.nonFood?.first() // improve to identify type
                add(
                    CategoryCardItem(
                        name = creatures?.category,
                        image = creatures?.image
                    )
                )
                val materials = model?.materials?.first()
                add(
                    CategoryCardItem(
                        name = materials?.category,
                        image = materials?.image
                    )
                )
                val monsters = model?.monsters?.first()
                add(
                    CategoryCardItem(
                        name = monsters?.category,
                        image = monsters?.image
                    )
                )
                val equipment = model?.equipment?.first()
                add(
                    CategoryCardItem(
                        name = equipment?.category,
                        image = equipment?.image
                    )
                )
                val treasure = model?.treasure?.first()
                add(
                    CategoryCardItem(
                        name = treasure?.category,
                        image = treasure?.image
                    )
                )
            }
        }
    }

    fun filterItems(): ArrayList<CategoryResponse> {
        return arrayListOf()
    }

    class HomeViewModelFactory(private val repository: ICompendiumRepository) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }
    }
}