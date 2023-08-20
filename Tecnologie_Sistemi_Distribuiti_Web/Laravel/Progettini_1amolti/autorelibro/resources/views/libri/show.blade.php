@extends('layout')

@section('title')
    Show
@endsection

@section('header')
    Mostra libro:
@endsection

@section('content')
    <p>Nome: {{$book->nome}}, composto da {{$book->pagine}}</p>

    <br>

    <p><a href='/'>Indietro</a></p>
    <p><a href='/book/{{$book->id}}/edit'>Modifica</a></p>

    <br>

    <form method="POST" action="/book/{{$book->id}}">
        @csrf
        @method('delete')
        <input type="submit" value="Cancella libro">
    </form>
@endsection