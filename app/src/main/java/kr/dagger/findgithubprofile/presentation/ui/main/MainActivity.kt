package kr.dagger.findgithubprofile.presentation.ui.main


import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kr.dagger.findgithubprofile.Constants.INTENT_ARGUMENT_USER_ID
import com.dagger.daggerhiltnetworkconnection.R
import kr.dagger.findgithubprofile.presentation.base.BaseActivity
import com.dagger.daggerhiltnetworkconnection.databinding.ActivityMainBinding
import kr.dagger.findgithubprofile.presentation.extensions.openActivity
import kr.dagger.findgithubprofile.presentation.extensions.removeFocusAndHideKeyboard
import kr.dagger.findgithubprofile.presentation.ui.main.detail.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.dagger.domain.state.ApiResponse

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        binding {
            activity = this@MainActivity
            vm = viewModel
        }
    }

    override fun onProcess() {
        initViewSetup()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.userInfo.observe(this) {
            when(it) {
                is ApiResponse.Success -> {
                    viewModel.isSearchEnabled.value = true
                }
                is ApiResponse.Error -> {
                    removeFocusAndHideKeyboard(this)
                    viewModel.isSearchEnabled.value = false
                    binding.topCl.snackbar(message = "존재하지 않는 사용자입니다.")
                }
                else -> {
                    viewModel.isSearchEnabled.value = false
                }
            }
        }
    }

    private fun initViewSetup() {
        lifecycleScope.launch {
            binding.edtSearchText.apply {
                textChange()
                    .debounce(500)
                    .onEach {
                        if(it?.length == 0) {
                            binding.imgGithub.setImageResource(R.drawable.ic_img_github)
                            viewModel.isSearchEnabled.value = false
                        }else {
                            viewModel.searchUserInfoResult(owner = it.toString())
                        }
                    }
                    .launchIn(this@launch)
            }
        }
    }

    fun moveUserDetailActivity() {
        openActivity(DetailActivity::class.java) {
            putString(INTENT_ARGUMENT_USER_ID, binding.edtSearchText.text.toString())
        }
    }
}
