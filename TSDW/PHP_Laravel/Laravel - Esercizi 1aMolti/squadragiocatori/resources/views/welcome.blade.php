@extends('layout')

@section('title')
    Index
@endsection

@section('header')
    Mostra squadre:
@endsection

@section('content')
    @foreach ($teams as $item)
        <a href="/teams/{{$item->id}}">{{$item->nome}}</a> <br>
        Nazione: {{$item->nazione}} <br>
        Data fondazione: {{$item->d_fondazione}} <br>
        <br>
    @endforeach

    <hr>

    <a href="/teams/create">Aggiungi squadra</a>
@endsection