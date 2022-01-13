package kr.dagger.findgithubprofile.presentation.ui.main.detail.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kr.dagger.findgithubprofile.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kr.dagger.domain.model.UserRepo
import kr.dagger.domain.state.ApiResponse
import kr.dagger.domain.usecase.GetPersonalGithubRepository
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getPersonalGithubRepository: GetPersonalGithubRepository) : BaseViewModel() {
    private val _repoData: MutableLiveData<ApiResponse<List<UserRepo>>> = MutableLiveData()
    val repoData: LiveData<ApiResponse<List<UserRepo>>>
        get() = _repoData


    fun searchUserInfoResult(owner: String) {
        viewModelScope.launch {
            getPersonalGithubRepository.execute(owner)
                .onStart { handleLoading(true) }
                .catch { handleLoading(false) }
                .collect { values ->
                    _repoData.value = values
                }
        }
    }
}