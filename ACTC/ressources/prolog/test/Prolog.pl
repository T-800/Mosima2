

alive(Player1).
alive(Player2).

Observe(X,Y):-
alive(X).
alive(Y).
jpl_call('Environment',observeAgents,[Y],R).
jpl_is_true(R).

Tire(X,Y):-
	alive(X).
	alive(Y).
	Observe(X,Y).
	jpl_call('Environment',shoot,[X,Y],R).
	jpl_is_true(R).

victorious(X,Y) :- 
	alive(X).
	killed(Y).
	

	