package io.kraftsman.services

import com.github.javafaker.Faker
import io.kraftsman.dtos.Contact
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.hours
import kotlin.time.minutes

class ContactService {
    private val faker = Faker(Locale("en"))
    private val currentMoment = Clock.System.now()
    private val timeZone = TimeZone.of("Asia/Taipei")

    @ExperimentalTime
    fun generate(amount: Int = 10): List<Contact> {
        return (1..amount).map {
            Contact(
                id = it.toLong(),
                name = faker.name().fullName(),
                email = faker.internet().emailAddress(),
                createdAt = currentMoment.toLocalDateTime(timeZone).toString(),
                updatedAt = (currentMoment + (1..12).random().hours + (10..50).random().minutes).toLocalDateTime(timeZone).toString()
            )
        }
    }
}