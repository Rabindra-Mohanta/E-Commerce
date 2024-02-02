package alkusi.mahato.e_commerce.screens.Home.Model

import alkusi.mahato.e_commerce.screens.NormalData

data class FemaleDataRes(val male: FemaleData)
data class FemaleData(val traditional: List<NormalData>, val western: List<NormalData>)