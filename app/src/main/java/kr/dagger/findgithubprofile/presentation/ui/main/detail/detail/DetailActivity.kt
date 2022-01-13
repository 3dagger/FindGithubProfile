package kr.dagger.findgithubprofile.presentation.ui.main.detail.detail

import android.os.Bundle
import androidx.activity.viewModels
import kr.dagger.findgithubprofile.Constants.INTENT_ARGUMENT_USER_ID
import com.dagger.daggerhiltnetworkconnection.R
import com.dagger.daggerhiltnetworkconnection.databinding.ActivityDetailBinding
import kr.dagger.findgithubprofile.presentation.adapter.recyclerview.RepositoryRecyclerViewAdapter
import kr.dagger.findgithubprofile.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kr.dagger.domain.state.ApiResponse

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var repoAdapter: RepositoryRecyclerViewAdapter
    var owner: String? = null

    override fun initView(savedInstanceState: Bundle?) {
        binding {
            activity = this@DetailActivity
            vm = viewModel
        }

        if(intent != null) owner = intent.getStringExtra(INTENT_ARGUMENT_USER_ID)

        viewModel.searchUserInfoResult(owner = owner ?: "")
    }

    override fun onProcess() {
        initViewSetup()
        subscribeObservers()
    }

    private fun initViewSetup() {
        repoAdapter = RepositoryRecyclerViewAdapter()
        binding.repoRv.apply {
            adapter = repoAdapter
        }
    }

    private fun subscribeObservers() {
        viewModel.repoData.observe(this) {
            when(it) {
                is ApiResponse.Success -> {
                    viewModel.handleLoading(false)
                    it.data?.let { userRepo -> repoAdapter.setItems(userRepo) }
                }
                is ApiResponse.Error -> {
                    viewModel.handleLoading(false)
                }
            }
        }
    }

}