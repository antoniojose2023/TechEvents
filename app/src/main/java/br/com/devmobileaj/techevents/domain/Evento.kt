package br.com.devmobileaj.techevents.domain

import java.io.Serializable

data class Evento(
    val address: String,
    val category: String,
    val city: String,
    val date: String,
    val description: String,
    val endTime: String,
    val eventUrl: String,
    val id: Int,
    val localeSpec: String,
    val organizer: String,
    val price: String,
    val registeredParticipants: Int,
    val startTime: String,
    val state: String,
    val title: String,
    val totalSeats: Int,
    val type: String
): Serializable