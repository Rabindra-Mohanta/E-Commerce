package alkusi.mahato.e_commerce.screens

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ShoppingFragmentViewModel @Inject constructor() : BaseViewModel() {

    var normalData = ObservableField<NormalData>()
    var itemCount = MutableLiveData<Int>(1)

    fun onIncrementClick() {
        itemCount.value?.let {
            if (it > 0) {
                var count = it
                itemCount.value = it+1
                //price
            }
        }
    }

    fun onDecrementClick() {
        itemCount.value?.let {
            if (it > 1 ) {
                itemCount.value = it-1
            }
        }
    }

}