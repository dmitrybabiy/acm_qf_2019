@echo off
SETLOCAL ENABLEDELAYEDEXPANSION
for %%I in (input*.txt) do (
	echo %%I
	set infile=%%~nI
	set outfile=!infile:input=output!.txt
	java Main < %%I > !outfile!
)
