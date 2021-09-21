package com.keonkotlin.keon_kotlin.member.view

import com.keonkotlin.keon_kotlin.common.model.BaseView
import lombok.Data

@Data
class UserView: BaseView() {

    var name = ""
    var email = ""
    var password = ""
}