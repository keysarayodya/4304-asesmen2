package org.d3if0044.bangundatar.data.network

class UniversityEntity : ArrayList<UniversityEntityItem>()

data class UniversityEntityItem(
    val province: String,
    val web_pages: List<String>,
    val alpha_two_code: String,
    val country: String,
    val name: String,
    val domains: List<String>
)