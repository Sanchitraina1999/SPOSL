	START	100
A	DS	3
L1	MOVER	AREG, B
	ADD	AREG, C
	MOVEM	AREG, D
	MOVER	AREG, ="5"
	MOVER	AREG, ="5"
	MOVER	AREG, ="1"
D	EQU	A+1
	LTORG
L2	PRINT	D
	ORIGIN	A-1
	MOVER	AREG, ="7"
	LTORG
C	DC	'5'
	MOVER	AREG, ="5"
	ORIGIN	L2+1
	STOP
B	DC	'19'
	END

