package alkusi.mahato.e_commerce.screens

import androidx.databinding.ObservableField
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ShoppingFragmentViewModel @Inject constructor() : BaseViewModel() {

    var normalData = ObservableField<NormalData>()


}