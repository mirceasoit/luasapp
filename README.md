# RIM Employee LUAS App
RIM Employee LUAS App

A lot of RIM employees commute from city center to the office using LUAS (tram system in Dublin, Ireland) every day. To avoid waiting too much at the stops, people would like to have an app where it is possible to see trams forecast. So, to help our employee, write a simple app considering only the following requests:

Given I am a LUAS passenger
When I open the app from 00:00 – 12:00
Then I should see trams forecast from Marlborough LUAS stop towards Outbound

Given I am a LUAS passenger
When I open the app from 12:01 – 23:59
Then I should see trams forecast from Stillorgan LUAS stop towards Inbound

Given I am on the stop forecast info screen
When I tap on the refresh button
Then the forecast data should be updated

To get real time information, use LUAS Forecasting API
https://data.gov.ie/dataset/luas-forecasting-api

To retrieve information from a specific stop, consider the below URLs:

Marlborough
http://luasforecasts.rpa.ie/xml/get.ashx?action=forecast&stop=mar&encrypt=false

Stillorgan
http://luasforecasts.rpa.ie/xml/get.ashx?action=forecast&stop=sti&encrypt=false

Tools used:
- Luas Forecasting Api
- Retrofit v.2.9.0
- Simple XML v.2.5.0
- ConstraintLayout v.2.0.2
- ViewModel v.2.2.0
- LiveData v.2.2.0
- RecyclerView v.1.1.0
