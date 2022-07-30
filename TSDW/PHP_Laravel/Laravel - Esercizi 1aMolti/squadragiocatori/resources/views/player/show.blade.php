@extends('layout')

@section('title')
    Show
@endsection

@section('header')
    Vista giocatore:
@endsection

@section('content')
    <h3>{{$player->nome}}</h3>
    Eta: {{$player->eta}} <br>
    <hr>

    <a href="/teams/{{$player->id_team}}">Indietro</a> <br>
    <a href="/players/{{$player->id}}/edit">Modifica giocatore</a>

    <form action="/players/{{$player->id}}" method="POST">
        @csrf
        @method('delete')
        <input type="submit" value="Elimina giocatore">
    </form>
@endsection