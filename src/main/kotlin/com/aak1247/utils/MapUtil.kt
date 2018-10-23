package com.aak1247.utils

import java.util.regex.Pattern

class MapUtil {
    fun getStringFromMap(map: Map<*, *>, name: String): String? {
        var map: Map<*, *>? = map
        val listPattern = ".*\\[\\d*]"
        val nameList = name.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var count = 0
        var curKey = nameList[0]
        var curValue: Any? = null
        while (count < nameList.size) {
            if (Pattern.matches(listPattern, curKey)) {
                //check if it a list
                val curKeyList = curKey.split("(\\[|])".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val curKeyName = curKeyList[0]
                val index = Integer.parseInt(curKeyList[1])
                //get the list
                curValue = map?.get(curKeyName)
                //get the list element as map
                curValue = (curValue as List<*>)[index]
                if (curValue is String) {
                    return curValue
                }
                curKey = nameList[++count]
                map = curValue as Map<*, *>?
            }
            //next level
            curValue = map?.get(curKey)
            if (curValue is Map<*, *>) {
                map = curValue
                curKey = nameList[++count]
            } else return curValue as? String ?: curValue!!.toString()
        }
        return curValue as String?
    }

    fun getObjFromMap(map: Map<*, *>, name: String): Any? {
        var map = map
        val listPattern = ".*\\[\\d*]"
        val nameList = name.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var count = 0
        var curKey = nameList[0]
        var curValue: Any? = null
        while (count < nameList.size) {
            if (Pattern.matches(listPattern, curKey)) {
                //check if it a list
                val curKeyList = curKey.split("(\\[|])".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val curKeyName = curKeyList[0]
                val index = Integer.parseInt(curKeyList[1])
                //get list
                curValue = map[curKeyName]
                //get the list element as map
                curValue = (curValue as List<*>)[index]
                if (curValue is String) {
                    return curValue
                }
                curKey = nameList[++count]
                map = (curValue as Map<*, *>?)!!
            }
            //next level
            curValue = map[curKey]
            if (curValue is Map<*, *>) {
                map = curValue
                curKey = nameList[++count]
            } else return curValue as? String ?: curValue
        }
        return curValue
    }
}