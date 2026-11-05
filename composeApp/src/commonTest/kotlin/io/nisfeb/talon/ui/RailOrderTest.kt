package io.nisfeb.talon.ui

import kotlin.test.Test
import kotlin.test.assertEquals

class RailOrderTest {
    @Test
    fun `dragged items swap their places and hidden ones keep theirs`() {
        val full = listOf(RailItem.Home, RailItem.Chats, RailItem.Mail, RailItem.Activity, RailItem.Settings)
        // Mail is hidden; Settings is dragged above Chats.
        val got = mergeRailOrder(full, listOf(RailItem.Home, RailItem.Settings, RailItem.Chats, RailItem.Activity))
        assertEquals(listOf(RailItem.Home, RailItem.Settings, RailItem.Mail, RailItem.Chats, RailItem.Activity), got)
    }

    @Test
    fun `hidden defaults start off the rail, and Chats can never be hidden`() {
        val none = emptyMap<RailItem, Boolean>()
        for (item in HIDDEN_BY_DEFAULT) assertEquals(false, none.isVisible(item), "$item")
        assertEquals(true, none.isVisible(RailItem.Chats))
        assertEquals(true, mapOf(RailItem.Mail to true).isVisible(RailItem.Mail), "shown once chosen")
    }
}
