@extends('layout')

@section('title')
    Show
@endsection

@section('header')
    {{$state->nome}} <br>
    Popolazione: {{$state->popolazione}}
@endsection

@section('content')
    @foreach ($cities as $city)
        <div>
            id_stato: {{$city->id_states}} <br>
            Città: {{$city->nome}} <br>
            Regione: {{$city->regione}}
        </div>
        <br>
        <br>
    @endforeach

    <p><a href="/states/{{$state->id}}/edit">Modifica stato</a></p>
    <p><a href='/'>Indietro</a></p>

    <form action="/cities/create" method="GET">
        @csrf
        <input type="submit" value="Aggiungi città">
        <input type="hidden" name="id_states" value={{$state->id}} >
    </form>

    <form action="/states/{{$state->id}}" method="POST">
        @csrf
        @method("delete")
        <input type="submit" value="Cancella">
    </form>
@endsection