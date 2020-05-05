package com.itis.restproject.server.scopes

import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.config.Scope
import java.util.*
import kotlin.collections.HashMap

class AuthenticationScope : Scope {

    private val scopedObjects = Collections.synchronizedMap(HashMap<String, Any>())
    private val destructionCallbacks = Collections.synchronizedMap(HashMap<String, Runnable>())
    override fun resolveContextualObject(key: String): Any? {
        return null
    }

    override fun remove(name: String): Any? {
        return null
    }

    override fun get(name: String, objectFactory: ObjectFactory<*>): Any {
        if (!scopedObjects.containsKey(name)) {
            scopedObjects[name] = objectFactory.getObject();
        }
        return scopedObjects[name] ?: throw IllegalArgumentException("Vse ochen' ploho")
    }

    override fun registerDestructionCallback(name: String, callback: Runnable) {

    }

    override fun getConversationId(): String? {
        return null
    }

}