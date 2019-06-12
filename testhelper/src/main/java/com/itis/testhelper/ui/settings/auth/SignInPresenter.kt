package com.itis.testhelper.ui.settings.auth

import com.itis.testhelper.repository.UserRepository
import com.itis.testhelper.ui.base.BasePresenter
import com.itis.testhelper.utils.DEFAULT_PAT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInPresenter(
        private var view: SignInView,
        private val userRepository: UserRepository
) : BasePresenter(view) {

    fun onLoginClick(login: String, password: String, note: String) {
        launch {
            invokeSuspend {
                val checkNote = if (note.isEmpty()) DEFAULT_PAT else note
                val result = userRepository.getAuthAsync(login, password, checkNote)
                launch(Dispatchers.IO) {
                    result.token.let {
                        userRepository.saveAuthToken(it)
                        userRepository.saveUser(userRepository.getUserByTokenAsync())
                    }
                }
                view.successSignIn(result.token)
            }
        }
    }
}