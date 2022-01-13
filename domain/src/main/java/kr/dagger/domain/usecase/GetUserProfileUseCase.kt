package kr.dagger.domain.usecase

import kr.dagger.domain.state.ApiResponse
import kr.dagger.domain.model.UserProfile
import kr.dagger.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(private val mainRepository: MainRepository) {
    /**
     * @author : 이수현
     * @Date : 2021/12/27 7:03 오후
     * @Description : 깃헙 유저정보 호출
     * @History :
     *
     **/
    fun execute(owner: String?) : Flow<ApiResponse<UserProfile>> {
        return mainRepository.getUserInfo(owner)
    }

}