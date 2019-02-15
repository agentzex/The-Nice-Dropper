# The-Nice-Dropper
An example of how a benign app can be uploaded to Google Play, hiding it's malicious code remotly, then on a specific time, downloading it using a dropper and dynamically loading it from a .dex file
/
Step ONE:
The user downloads a benign looking app, unknowing it's malicious activties
<img src="https://github.com/dor-alt/The-Nice-Dropper/raw/master/TheNiceDropper/1.png" />

Step TWO:
In the background the app downloads a pre-compiled .dex file made by the attacker (in order to do so, you need to create a seperate module using Android studio, then convert the compiled .jar to .dex using: 
dx.bat --dex --output payload.dex  input.jar
On windows, the path to dx will be <your-Android-path>\bin\build-tools\<some-version>\ )\
The .dex file is loaded into memory and the malicious code starts running
<img src="https://github.com/dor-alt/The-Nice-Dropper/raw/master/TheNiceDropper/2.png" />
