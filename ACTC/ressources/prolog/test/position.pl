use_module(library(jpl)).


%initial state

envie(X). % etre en vie 
envie(Y).
blesser(X).
blesser(Y).


observeAgents(X):-
	alive(X),
	not(blesser(X)),
	jpl_call('SmartAgent',observeAgent,[],@void),
	jpl_call('SmartAgent',observeAgentVerification,[],R),%Verifier que bien observeAgent action a ete faite
	jpl_is_true(R).


observeEnvironnment(X):-
	alive(X),
	not(blesser(X)),
	not(EnnemiEnvu(Y)),
	jpl_call('SmartAgent',observeEnvironnement,[],@void),
	jpl_call('SmartAgent',observeEnvironnementVerification,[],R),%Verifier que bien observeAgent action a ete faite
	jpl_is_true(R).


victorious(X,Y) :- 
	alive(X),
	not(alive(Y)).
	jpl_call('ObserveBehaviour',victoryVerification,[],R),%Verifier que bien observeAgent action a ete faite
	jpl_is_true(R).

tirer(X,Y) :-
	alive(X),
	not(blesser(X)),
	EnnemiEnvu(Y),
	jpl_call('ShootBehaviour',shoot,[Y],@void),
	jpl_call('ShootBehaviour',shootedVerification,[Y],R),
	jpl_is_true(R).
	

PointInteret(X,Y) :-
  	Lahauteur(X),
  	or(Depth(X)),
    or(ChampDeVision(X)),
    jpl_call('PointDinteret',PointDinteret,[],@void),
    jpl_call('PointDinteret',PointDinteretVerification,[Y],R),
	jpl_is_true(R).	
	
fuite(X,Y) :-
	alive(X),
	alive(Y),
	tirer(Y,X),
	blesser(X),
    jpl_call('FuiteBehaviour',fuite,[],@void),
    jpl_call('FuiteBehaviour',FuiteVerification,[Y],R),
	jpl_is_true(R).	
	
follow(X,Y) :-
	random(X),
	observe(X,Y),
	not(tirer(X,Y)),
	not(tirer(Y,X)),
	not(blesser(X)),
	blesser(Y),
	jpl_call('FollowBehaviour',follow,[],@void),
	jpl_call('FollowBehaviour',follow,[],R),
	jpl_is_true(R).	
	
	
%cettefonction doit verifier qu'il n'y a pas l'ennemi en vue(je pense c deja code par le prof dans environnement
%la fonction que j'utilise pour scanner l'environnement dans shootBehaviour elle rend la liste des agents presents
% du coup faut une fonction qui l'appelle et si c vide, sa rend false dans EnnemiEnVu(prolog).	
EnnemiEnvu(Y):-
	jpl_call('ObserveBehaviour',ObserveAgentBoolean,[],R),
	jpl_is_true(R).	

%J'ai besoin de verifier qu'il est blesse
blesse(X):-
	jpl_call('Environnement',Blesser,[X],R),
	jpl_is_true(R).		
	
%Avoir un boolean pour verifier si oui ou non 
Lahauteur(X) :-
	jpl_call('SmartAgent',supAvgAlt,[X],R),
	jpl_is_true(R).

%Verifier le Depth
Depth(X) :-
	jpl_call('SmartAgent',supAvgMaxDepth,[X],R),
	jpl_is_true(R).
	
ChampDeVision(X) :-
	jpl_call('SmartAgent',supAvgFov,[],R),
	jpl_is_true(R).