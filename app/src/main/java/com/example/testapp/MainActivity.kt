package com.example.testapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rxjava.User
import com.example.rxjavaexample.network.model.Airline
import com.example.rxjavaexample.network.model.Price
import com.example.rxjavaexample.network.model.Ticket
import io.reactivex.Observable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        printTickets()
        filterTickets()
    }

    //val from: String, val to: String,
    //                  val departure: String, val arrival: String, val duration: String, val instructions: String,
    //                  val flightNumber: String, val numberOfStops: Int,
    //                  val airline: Airline, var price: Price?

    //data class Airline(val id: Int, val name: String, val logo: String)
    //data class Price(val price: Float, val seats: String, val currency: String,
    //                 val flightNumber: String,
    //                 val from: String, val to: String)

    @SuppressLint("CheckResult")
    fun printTickets() {
        Observable.just(Ticket("Tacoma", "LasVegas", "1000", "1700",
                "0600", "get off my plane", "199", 0,
                Airline(1, "Alpha Air", "Big A"),
                Price(7f,"2","USD","Redline","Tacoma","LasVegas")),
                Ticket("Houston", "Atlanta", "1200", "1900",
                        "0800", "go to the hotel", "33", 2,
                        Airline(2, "Beta Air", "Big B"),
                        Price(22f,"1","USD","Blueline","Houston","Atlanta")),
                Ticket("Dallas", "Alberquerque", "0600", "1200",
                        "0600", "check out the gift shop", "22", 1,
                        Airline(3, "Gamma Air", "Big C"),
                        Price(11f,"3","USD","Greenline","Dallas","Alberquerque"))
        )
                .subscribe { x ->
                    println("Tickets = {$x}") }
    }

    @SuppressLint("CheckResult")
    fun filterTickets(){
        Observable.just(Ticket("Tacoma", "LasVegas", "1000", "1700",
                "0600", "get off my plane", "199", 0,
                Airline(1, "Alpha Air", "Big A"),
                Price(7f,"2","USD","Redline","Tacoma","LasVegas")),
                Ticket("Houston", "Atlanta", "1200", "1900",
                        "0800", "go to the hotel", "33", 2,
                        Airline(2, "Beta Air", "Big B"),
                        Price(22f,"1","USD","Blueline","Houston","Atlanta")),
                Ticket("Dallas", "Alberquerque", "0600", "1200",
                        "0600", "check out the gift shop", "22", 1,
                        Airline(3, "Gamma Air", "Big C"),
                        Price(11f,"3","USD","Greenline","Dallas","Alberquerque"))
        )
                .filter{x ->
                    x.airline.name == "Beta Air"
                }
                .filter{x ->
                    x.price?.price == 22f
                }
                .subscribe { x ->
                    println("Tickets = {$x}") }
    }

    @SuppressLint("CheckResult")
    fun mapTickets(){
        val tix1 = Observable.just(Ticket("Tacoma", "LasVegas", "1000", "1700",
                "0600", "get off my plane", "199", 0,
                Airline(1, "Alpha Air", "Big A"),
                Price(7f,"2","USD","Redline","Tacoma","LasVegas")),
                Ticket("Houston", "Atlanta", "1200", "1900",
                        "0800", "go to the hotel", "33", 2,
                        Airline(2, "Beta Air", "Big B"),
                        Price(22f,"1","USD","Blueline","Houston","Atlanta")),
                Ticket("Dallas", "Alberquerque", "0600", "1200",
                        "0600", "check out the gift shop", "22", 1,
                        Airline(3, "Gamma Air", "Big C"),
                        Price(11f,"3","USD","Greenline","Dallas","Alberquerque"))
        )
        val tix2 = Observable.just(Ticket("Washington DC", "Langley", "0900", "1300",
                "0400", "get to the choppah", "333", 1,
                Airline(2, "Alpha Air", "Big A"),
                Price(7f,"2","USD","Redline","Tacoma","LasVegas")),
                Ticket("Houston", "Atlanta", "1200", "1900",
                        "0800", "go to the hotel", "33", 2,
                        Airline(2, "Beta Air", "Big B"),
                        Price(22f,"1","USD","Blueline","Houston","Atlanta")),
                Ticket("Dallas", "Alberquerque", "0600", "1200",
                        "0600", "check out the gift shop", "22", 1,
                        Airline(3, "Gamma Air", "Big C"),
                        Price(11f,"3","USD","Greenline","Dallas","Alberquerque"))
        )
                Observable.concat(tix1, tix2)
                        .map { x ->
                            Price(22f, "1","USD","Blueline","Houston","Atlanta")
                        }
                .subscribe { x ->
                    println("Tickets = $x")
                }
    }

}
