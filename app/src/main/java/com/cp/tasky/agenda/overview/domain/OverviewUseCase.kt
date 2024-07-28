package com.cp.tasky.agenda.overview.domain

import com.cp.tasky.agenda.overview.domain.model.Overview
import com.cp.tasky.agenda.shared.data.model.AgendaItem

object AgendaOverviewUseCase {

    private const val DEFAULT_INITIALS = "NA"

    /**
     * All agenda items should be ordered
     * ascending by time
     */
    fun sortAgendaItemsByAscendingTime(items: Overview): List<AgendaItem> {
        val tempList = mutableListOf<AgendaItem>()
        tempList.addAll(items.events)
        tempList.addAll(items.tasks)
        tempList.addAll(items.reminders)

        return tempList.sortedBy { it.remindAt }
    }

    /**
     * On the right of the toolbar, there should be a profile icon consisting of the
     * logged in user’s full name initials
     * * if the full name is First Last, display the initials → PL
     * * If the full name consists of one word First, display the first 2 characters → FI
     * * If there is a middle name, display the initials of the first and last name,
     * for First Middle Last , it would therefore be FL
     */
    fun getProfileIconInitials(userName: String): String {
        // Remove trailing and leading space
        val trimmedName = userName.trim()

        // e.g: "   A"
        if (trimmedName.length <= 2) return trimmedName

        // Split the name into parts by space
        val nameParts = trimmedName.split(regex = Regex("^\\s+$"))

        return try {
            when (nameParts.size) {
                // One word
                1 -> nameParts[0].substring(0..1).uppercase()

                // Two words
                2 -> "${nameParts[0][0]}${nameParts[1][0]}".uppercase()

                // Three words or more
                else -> "${nameParts[0][0]}${nameParts.last()[0]}".uppercase()
            }
        } catch (e: Exception) {
            DEFAULT_INITIALS
        }
    }
}
