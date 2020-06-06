package com.paulniu.bill_base_lib.util

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.NonNull
import java.lang.reflect.InvocationTargetException
import java.util.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/6 2:22 PM
 * desc: SharedPreference操作工具类
 */
class SPUtil {

    private val DEFAULT_FILENAME = "iyingbill"

    private var sp: SharedPreferences

    private constructor(spName: String) {
        sp = applicationByReflect
            .getSharedPreferences(spName, Context.MODE_PRIVATE)
    }

    private constructor(spName: String, mode: Int) {
        sp = applicationByReflect.getSharedPreferences(spName, mode)
    }
    /**
     * Put the string value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use [SharedPreferences.Editor.commit],
     * false to use [SharedPreferences.Editor.apply]
     */
    /**
     * Put the string value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    @JvmOverloads
    fun put(
        @NonNull key: String?,
        value: String?,
        isCommit: Boolean = false
    ) {
        if (isCommit) {
            sp.edit().putString(key, value).commit()
        } else {
            sp.edit().putString(key, value).apply()
        }
    }

    /**
     * Return the string value in sp.
     *
     * @param key The key of sp.
     * @return the string value if sp exists or `""` otherwise
     */
    fun getString(@NonNull key: String?): String? {
        return getString(key, "")
    }

    /**
     * Return the string value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the string value if sp exists or `defaultValue` otherwise
     */
    fun getString(@NonNull key: String?, defaultValue: String?): String? {
        return sp.getString(key, defaultValue)
    }
    /**
     * Put the int value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use [SharedPreferences.Editor.commit],
     * false to use [SharedPreferences.Editor.apply]
     */
    /**
     * Put the int value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    @JvmOverloads
    fun put(
        @NonNull key: String?,
        value: Int,
        isCommit: Boolean = false
    ) {
        if (isCommit) {
            sp.edit().putInt(key, value).commit()
        } else {
            sp.edit().putInt(key, value).apply()
        }
    }

    /**
     * Return the int value in sp.
     *
     * @param key The key of sp.
     * @return the int value if sp exists or `-1` otherwise
     */
    fun getInt(@NonNull key: String?): Int {
        return getInt(key, -1)
    }

    /**
     * Return the int value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the int value if sp exists or `defaultValue` otherwise
     */
    fun getInt(@NonNull key: String?, defaultValue: Int): Int {
        return sp.getInt(key, defaultValue)
    }
    /**
     * Put the long value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use [SharedPreferences.Editor.commit],
     * false to use [SharedPreferences.Editor.apply]
     */
    /**
     * Put the long value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    @JvmOverloads
    fun put(
        @NonNull key: String?,
        value: Long,
        isCommit: Boolean = false
    ) {
        if (isCommit) {
            sp.edit().putLong(key, value).commit()
        } else {
            sp.edit().putLong(key, value).apply()
        }
    }

    /**
     * Return the long value in sp.
     *
     * @param key The key of sp.
     * @return the long value if sp exists or `-1` otherwise
     */
    fun getLong(@NonNull key: String?): Long {
        return getLong(key, -1L)
    }

    /**
     * Return the long value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the long value if sp exists or `defaultValue` otherwise
     */
    fun getLong(@NonNull key: String?, defaultValue: Long): Long {
        return sp.getLong(key, defaultValue)
    }
    /**
     * Put the float value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use [SharedPreferences.Editor.commit],
     * false to use [SharedPreferences.Editor.apply]
     */
    /**
     * Put the float value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    @JvmOverloads
    fun put(
        @NonNull key: String?,
        value: Float,
        isCommit: Boolean = false
    ) {
        if (isCommit) {
            sp.edit().putFloat(key, value).commit()
        } else {
            sp.edit().putFloat(key, value).apply()
        }
    }

    /**
     * Return the float value in sp.
     *
     * @param key The key of sp.
     * @return the float value if sp exists or `-1f` otherwise
     */
    fun getFloat(@NonNull key: String?): Float {
        return getFloat(key, -1f)
    }

    /**
     * Return the float value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the float value if sp exists or `defaultValue` otherwise
     */
    fun getFloat(@NonNull key: String?, defaultValue: Float): Float {
        return sp.getFloat(key, defaultValue)
    }
    /**
     * Put the boolean value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use [SharedPreferences.Editor.commit],
     * false to use [SharedPreferences.Editor.apply]
     */
    /**
     * Put the boolean value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    @JvmOverloads
    fun put(
        @NonNull key: String?,
        value: Boolean,
        isCommit: Boolean = false
    ) {
        if (isCommit) {
            sp.edit().putBoolean(key, value).commit()
        } else {
            sp.edit().putBoolean(key, value).apply()
        }
    }

    /**
     * Return the boolean value in sp.
     *
     * @param key The key of sp.
     * @return the boolean value if sp exists or `false` otherwise
     */
    fun getBoolean(@NonNull key: String?): Boolean {
        return getBoolean(key, false)
    }

    /**
     * Return the boolean value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the boolean value if sp exists or `defaultValue` otherwise
     */
    fun getBoolean(@NonNull key: String?, defaultValue: Boolean): Boolean {
        return sp.getBoolean(key, defaultValue)
    }
    /**
     * Put the set of string value in sp.
     *
     * @param key      The key of sp.
     * @param value    The value of sp.
     * @param isCommit True to use [SharedPreferences.Editor.commit],
     * false to use [SharedPreferences.Editor.apply]
     */
    /**
     * Put the set of string value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    @JvmOverloads
    fun put(
        @NonNull key: String?,
        value: Set<String?>?,
        isCommit: Boolean = false
    ) {
        if (isCommit) {
            sp.edit().putStringSet(key, value).commit()
        } else {
            sp.edit().putStringSet(key, value).apply()
        }
    }

    /**
     * Return the set of string value in sp.
     *
     * @param key The key of sp.
     * @return the set of string value if sp exists
     * or `Collections.<String>emptySet()` otherwise
     */
    fun getStringSet(@NonNull key: String?): Set<String>? {
        return getStringSet(key, emptySet<String>())
    }

    /**
     * Return the set of string value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the set of string value if sp exists or `defaultValue` otherwise
     */
    fun getStringSet(
        @NonNull key: String?,
        defaultValue: Set<String?>?
    ): Set<String>? {
        return sp.getStringSet(key, defaultValue)
    }

    /**
     * Return all values in sp.
     *
     * @return all values in sp
     */
    fun getAll(): Map<String, *> {
        return sp.all
    }

    /**
     * Return whether the sp contains the preference.
     *
     * @param key The key of sp.
     * @return `true`: yes<br></br>`false`: no
     */
    operator fun contains(@NonNull key: String?): Boolean {
        return sp.contains(key)
    }
    /**
     * Remove the preference in sp.
     *
     * @param key      The key of sp.
     * @param isCommit True to use [SharedPreferences.Editor.commit],
     * false to use [SharedPreferences.Editor.apply]
     */
    /**
     * Remove the preference in sp.
     *
     * @param key The key of sp.
     */
    @JvmOverloads
    fun remove(@NonNull key: String?, isCommit: Boolean = false) {
        if (isCommit) {
            sp.edit().remove(key).commit()
        } else {
            sp.edit().remove(key).apply()
        }
    }
    /**
     * Remove all preferences in sp.
     *
     * @param isCommit True to use [SharedPreferences.Editor.commit],
     * false to use [SharedPreferences.Editor.apply]
     */
    /**
     * Remove all preferences in sp.
     */
    @JvmOverloads
    fun clear(isCommit: Boolean = false) {
        if (isCommit) {
            sp.edit().clear().commit()
        } else {
            sp.edit().clear().apply()
        }
    }

    companion object {
        private val SP_UTIL_MAP: MutableMap<String, SPUtil> =
            HashMap()

        val pictureSpUtil: SPUtil?
            get() = getInstance("PictureSpUtils")

        /**
         * Return the single [SPUtil] instance
         *
         * @return the single [SPUtil] instance
         */
        val instance: SPUtil?
            get() = getInstance("", Context.MODE_PRIVATE)

        /**
         * Return the single [SPUtil] instance
         *
         * @param mode Operating mode.
         * @return the single [SPUtil] instance
         */
        fun getInstance(mode: Int): SPUtil? {
            return getInstance("", mode)
        }

        /**
         * Return the single [SPUtil] instance
         *
         * @param spName The name of sp.
         * @return the single [SPUtil] instance
         */
        fun getInstance(spName: String): SPUtil? {
            return getInstance(spName, Context.MODE_PRIVATE)
        }

        /**
         * Return the single [SPUtil] instance
         *
         * @param spName The name of sp.
         * @param mode   Operating mode.
         * @return the single [SPUtil] instance
         */
        fun getInstance(spName: String, mode: Int): SPUtil? {
            var spName = spName
            if (isSpace(spName)) spName = "spUtils"
            var spUtils = SP_UTIL_MAP[spName]
            if (spUtils == null) {
                synchronized(SPUtil::class.java) {
                    spUtils = SP_UTIL_MAP[spName]
                    if (spUtils == null) {
                        spUtils = SPUtil(spName, mode)
                        SP_UTIL_MAP[spName] = spUtils!!
                    }
                }
            }
            return spUtils
        }

        private fun isSpace(s: String?): Boolean {
            if (s == null) return true
            var i = 0
            val len = s.length
            while (i < len) {
                if (!Character.isWhitespace(s[i])) {
                    return false
                }
                ++i
            }
            return true
        }

        private val applicationByReflect: Application
            private get() {
                try {
                    @SuppressLint("PrivateApi") val activityThread =
                        Class.forName("android.app.ActivityThread")
                    val thread =
                        activityThread.getMethod("currentActivityThread").invoke(null)
                    val app = activityThread.getMethod("getApplication").invoke(thread)
                        ?: throw NullPointerException("u should init first")
                    return app as Application
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                }
                throw NullPointerException("u should init first")
            }
    }
}
