@extends('layout')

@section('title')
    Autori
@endsection

@section('header')
    Lista autori:
@endsection

@section('content')
    <ul>
        @foreach ($autori as $autore)
            <li><a href='author/{{$autore->id}}'>{{$autore->nome}}</a> nato il {{$autore->nascita}} </li>
        @endforeach
    </ul>

    <a href='/author/create'>Aggiungi un nuovo autore</a>
@endsection