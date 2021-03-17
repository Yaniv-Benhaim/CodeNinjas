package tech.gamedev.codeninjas.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tech.gamedev.codeninjas.data.models.categories.QuickKnowledge
import tech.gamedev.codeninjas.repo.LessonRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val lessonRepository: LessonRepository) : ViewModel() {

    private val _quickKnowledge = MutableLiveData<QuickKnowledge?>()
    val quickKnowledge: LiveData<QuickKnowledge?> = _quickKnowledge

    fun getQuickKnowledgeVideo(language: String, topic: String) = viewModelScope.launch {
       _quickKnowledge.value = lessonRepository.getQuickKnowledge(language, topic)
        Log.d("QUICK", _quickKnowledge.value.toString())
    }

}