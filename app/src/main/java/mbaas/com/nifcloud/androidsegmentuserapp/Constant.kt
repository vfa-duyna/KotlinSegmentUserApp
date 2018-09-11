package mbaas.com.nifcloud.androidsegmentuserapp

import java.util.*

/**
 * 定数宣言
 */
object Constant {

    // 既存ユーザーフィールド11種(更新不可項目)
    val ignoreKeys = Arrays.asList(
            "objectId",
            "userName",
            "password",
            "temporaryPassword",
            "mailAddress",
            "authData",
            "sessionInfo",
            "sessionToken",
            "mailAddressConfirm",
            "acl",
            "createDate",
            "updateDate"
    )

}
