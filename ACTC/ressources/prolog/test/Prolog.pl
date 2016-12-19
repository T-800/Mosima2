
Observe(X,Y):-
jpl_call('Environment',observeAgents,[Y],R).
jpl_is_true(R).


Tire(X,Y):-
	Observe(X,Y).
	
.
