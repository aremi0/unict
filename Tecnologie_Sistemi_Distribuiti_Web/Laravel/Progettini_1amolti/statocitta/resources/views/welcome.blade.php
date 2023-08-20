@extends('layout')

@section('title')
    Welcome!
@endsection

@section('header')
    Lista stati:
@endsection

@section('content')
<ul>
    @foreach ($states as $item)
        <li>
            <a href='/states/{{$item->id}}'>{{$item->nome}}</a> - {{$item->popolazione}}</li>
    @endforeach
</ul>

<p><a href='/states/create'>Aggiungi nuovo stato</a></p>
@endsection