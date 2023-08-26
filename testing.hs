--Testeo de funciones--
import Point
import City
import Quality
import Link
import Tunel
import Region

-- Modulo Point --
punto1 = newP 5 4
punto2 = newP (-8) (-5)
punto3 = newP 3 4
punto4 = newP 5 8
punto5 = newP 2 2

difP1P2 = difP punto1 punto2
-------------------

-- Modulo City --
olivos = newC "Olivos" punto1
martinez = newC "Martinez" punto2
laLucila = newC "La Lucila" punto3 
beccar = newC "Beccar" punto4
vicenteLopez = newC "Vicente Lopez" punto5

nameOlivos = nameC olivos
nameMartinez = nameC martinez

distanceOlivosMartinez = distanceC olivos martinez
distanceMartinezOlivos = distanceC martinez olivos
-------------------

-- Modulo Quality --
cableFibraOptica = newQ "Fibra Optica" 6 15.3
cableCoaxial = newQ "Coaxial" 4 22.4
cableParTrenzado = newQ "Par Trenzado" 3 24.6

capacidadFibraOptica = capacityQ cableFibraOptica
capacidadCoaxial = capacityQ cableCoaxial
capacidadParTrenzado = capacityQ cableParTrenzado

delayFibraOptica = delayQ cableFibraOptica
delayCoaxial = delayQ cableCoaxial
delayParTrenzado = delayQ cableParTrenzado
-------------------

-- Modulo Link --
linkOlivosMartinez = newL olivos martinez cableFibraOptica
linkMartinezLaLucila = newL martinez laLucila cableParTrenzado
linkLaLucilaBeccar = newL laLucila beccar cableParTrenzado
linkBeccarVicenteL = newL beccar vicenteLopez cableFibraOptica
linkMartinezMartinez = newL martinez martinez cableCoaxial

olivosConnectedByL = connectsL olivos linkOlivosMartinez
olivosNotConnectedByL = connectsL olivos linkMartinezLaLucila

martinezOlivosLinked = linksL martinez olivos linkOlivosMartinez
martinezOlivosNotLinked = linksL martinez olivos linkMartinezLaLucila
martinezLaLucilaLinked = linksL martinez laLucila linkMartinezLaLucila 
martinezMartinezExcept = linksL martinez martinez linkMartinezLaLucila
martinezMartinezExcept2 = linksL martinez martinez linkMartinezMartinez

olivosMartinezCapacity = capacityL linkOlivosMartinez
martinezLaLucilaCapacity = capacityL linkMartinezLaLucila

olivosMartinezDelay = delayL linkOlivosMartinez
martinezLaLucilaDelay = delayL linkMartinezLaLucila
-------------------

-- Modulo Tunel --
tunelMitre = newT [linkOlivosMartinez,linkMartinezLaLucila,linkLaLucilaBeccar]
tunelRetiro = newT [linkLaLucilaBeccar,linkBeccarVicenteL]
tunelSolitario = newT [linkOlivosMartinez]
tunelRandomCapacityExceeded = newT [linkMartinezLaLucila,linkMartinezLaLucila,linkMartinezLaLucila,linkMartinezLaLucila]

olivosBeccarConnected = connectsT olivos beccar tunelMitre
beccarOlivosConnected = connectsT beccar olivos tunelMitre
olivosLaLucilaNotConnected = connectsT olivos laLucila tunelMitre
laLucilaBeccarNotConnected = connectsT laLucila beccar tunelRetiro
olivosMartinezConnected = connectsT olivos martinez tunelSolitario
martinezOlivosConnected = connectsT martinez olivos tunelSolitario

tunelMitreUsesLink = usesT linkOlivosMartinez tunelMitre
tunelMitreNotUsesLink = usesT linkBeccarVicenteL tunelMitre

tunelMitreDelay = delayT tunelMitre
tunelRetiroDelay = delayT tunelRetiro
-------------------

-- Modulo Region --
zonaNorte = newR

foundOlivos = foundR zonaNorte olivos
foundMartinez = foundR foundOlivos martinez
foundLaLucila = foundR foundMartinez laLucila
foundBeccar = foundR foundLaLucila beccar

linkOlivosMartinez' = linkR foundBeccar olivos martinez cableFibraOptica
linkMartinezLaLucila' = linkR linkOlivosMartinez' martinez laLucila cableCoaxial

tunelOlivosLaLucila = tunelR linkMartinezLaLucila' [olivos,martinez,laLucila]
tunelOlivosMartinez = tunelR tunelOlivosLaLucila [olivos,martinez]

olivosLaLucilaConnectedR = connectedR tunelOlivosMartinez olivos laLucila
olivosBeccarNotConnectedR = connectedR tunelOlivosMartinez olivos beccar 
martinezBeccarNotConnectedR = connectedR tunelOlivosMartinez martinez beccar
martinezMartinezExcept' = connectedR tunelOlivosLaLucila martinez martinez

olivosMartinezLinkedR = linkedR tunelOlivosLaLucila olivos martinez
olivosLaLucilaNotLinkedR = linkedR tunelOlivosLaLucila olivos laLucila

olivosLaLucilaDelayR = delayR tunelOlivosLaLucila olivos laLucila
olivosMartinezDelayExcept = delayR tunelOlivosLaLucila olivos martinez

olivosMartinezAvCapac = availableCapacityForR tunelOlivosLaLucila olivos martinez
-------------------









