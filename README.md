# The-Nice-Dropper
A code example of how an attacker can upload a benign app to Google Play, hiding it's malicious code remotely, then, at a specific time, downloading it using a dropper and dynamically loading it to the app from a .dex file (the dynamically loaded .dex lib can be found under TheNiceDropper/pwnage/)\
Step ONE:\
\
The user downloads a benign looking app, unknowing its malicious activities\
<img src="https://github.com/dor-alt/The-Nice-Dropper/raw/master/TheNiceDropper/1.png" />
\
Step TWO:\
In the background the app downloads a pre-compiled .dex file made by the attacker (in order to do so, you need to create a separate module using Android studio, then convert the compiled .jar to .dex using:\
dx.bat --dex --output payload.dex  input.jar\
On windows, the path to dx will be:  \<your-Android-path>\bin\build-tools\<some-version>\ )\
The .dex file is then loaded into memory and the malicious code starts running\
<img src="https://github.com/dor-alt/The-Nice-Dropper/raw/master/TheNiceDropper/2.png" />
