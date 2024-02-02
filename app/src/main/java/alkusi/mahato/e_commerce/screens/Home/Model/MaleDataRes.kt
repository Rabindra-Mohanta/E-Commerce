package alkusi.mahato.e_commerce.screens.Home.Model

import alkusi.mahato.e_commerce.screens.NormalData

data class MaleDataRes(val male:MaleData)
data class MaleData(val traditional:List<NormalData>, val western:List<NormalData>)
