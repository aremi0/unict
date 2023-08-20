@extends('layout')

@section('title')
    Mostra autore
@endsection

@section('header')
    Mostra autore:
@endsection

@section('content')
    <p>Nome {{$author->nome}}, nato il {{$author->nascita}}</p>

    <h4>Lista dei suoi libri:</h4>
    <ul>
        @foreach ($libri as $libro)
            <li><a href='/book/{{$libro->id}}'>{{$libro->nome}}</a>, composto da {{$libro->pagine}} pagine</li>
        @endforeach
    </ul>

    <br>

    <p><a href='/'>Indietro</a></p>
    <p><a href='/author/{{$author->id}}/edit'>Modifica</a></p>

    <form method="GET" action="/book/create">
        @csrf
        <input type="hidden" name="id_autore" value="{{$author->id}}">
        <input type="submit" value="Aggiungi libro">
    </form>

    <br>

    <form method="POST" action="/author/{{$author->id}}">
        @csrf
        @method('delete')
        <input type="submit" value="Cancella autore">
    </form>
@endsection