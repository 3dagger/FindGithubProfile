package kr.dagger.findgithubprofile.presentation.ui.main.detail.web

import android.os.Bundle
import kr.dagger.findgithubprofile.Constants.INTENT_ARGUMENT_REPO_URL
import com.dagger.daggerhiltnetworkconnection.R
import com.dagger.daggerhiltnetworkconnection.databinding.ActivityRepoBinding
import kr.dagger.findgithubprofile.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoActivity : BaseActivity<ActivityRepoBinding>(R.layout.activity_repo) {
    var url: String? = null

    override fun initView(savedInstanceState: Bundle?) {
        binding {
            activity = this@RepoActivity
        }

        if(intent != null) url = intent.getStringExtra(INTENT_ARGUMENT_REPO_URL)
    }

    override fun onProcess() {
    }
}