@extends('layout')

@section('title')
    Show
@endsection

@section('header')
    Vista squadra:
@endsection

@section('content')
    <h3>{{$team->nome}}</h3>
    Nazione: {{$team->nazione}} <br>
    Data fondazione: {{$team->d_fondazione}} <br>
    <hr>

    <h3>Lista giocatori:</h3>
    @foreach ($giocatori as $item)
        {{$item->id}} - <a href="/players/{{$item->id}}">{{$item->nome}}</a> <br>
        Età: {{$item->eta}} <br>
        <br>
    @endforeach
    <hr>

    <a href="/">Indietro</a> <br>
    <a href="/teams/{{$team->id}}/edit">Modifica squadra</a> <br>

    <form action="/players/create" method="GET">
        <input type="hidden" name='id_team' value="{{$team->id}}">
        <input type="submit" value="Aggiungi nuovo giocatore">
    </form>

    <form action="/teams/{{$team->id}}" method="POST">
        @csrf
        @method('delete')
        <input type="submit" value="Elimina squadra">
    </form>
@endsection