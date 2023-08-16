module Tunel ( Tunel, newT, connectsT, usesT, delayT)
   where

import Point
import City
import Quality
import Link

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT linksList = Tun linksList
---

primerLink :: [Link] -> Link
primerLink linksList = (!!) linksList 0

ultimoLink :: [Link] -> Link
ultimoLink linksList = (!!) linksList (length(linksList) - 1)

---

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun linksList) =  (connectsL (city1) (primerLink(linksList))) && ((connectsL (city2) (ultimoLink(linksList))))


usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun linksList) = elem link linksList

delaySum :: [Float] -> Float
delaySum [] = 0
delaySum (x:xs) = x + delaySum(xs)

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun linksList) = delaySum ([delayL(x) | x <- linksList])

--Cities
olivos = newC "Olivos" (newP 5 4)
mtz = newC "Martinez" (newP 1 2)
berazategui = newC "Berazategui" (newP 8 4)
munro = newC "Munro" (newP 5 6)
merlo = newC "Merlo" (newP 19 8)
sanluis = newC "San Luis" (newP 15 9)
--Qualities
fibraOptica = newQ "Fibra Optica" 4 22.3
cableNormal = newQ "Cable" 2 15.4
--Links
sanIsidro = newL olivos mtz fibraOptica
otroMunicipio = newL berazategui munro cableNormal
provincia = newL merlo sanluis cableNormal
--Tuneles
tunelLaNoria= newT [sanIsidro,otroMunicipio]
tunelAvellaneda = newT [sanIsidro, otroMunicipio, provincia]
--Pruebas
listaDeLinks = [sanIsidro,otroMunicipio,provincia]
--(!!) listaDeLinks 0 
