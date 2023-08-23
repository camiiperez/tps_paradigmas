module Region ( Region, newR,foundR, linkR, tunelR,connectedR, linkedR, delayR, availableCapacityForR)
   where
import Point
import City
import Quality
import Link
import Tunel

data Region = Reg [City] [Link] [Tunel] deriving(Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region
foundR (Reg cities links tunels) city = (Reg (cities ++ [city]) links tunels)

linkR :: Region -> City -> City -> Quality -> Region 
linkR (Reg cities links tunels) city1 city2 quality = Reg cities ([newL city1 city2 quality] ++ links) tunels

filtraLink :: [Link] -> City -> City -> Link
filtraLink linksList city1 city2 = head [link | link <- linksList, linksL city1 city2 link]

primerosDos :: [a] -> [a]
primerosDos (x:y:_) = [x, y]
primerosDos _ = []  

obtenerParesDeCiudades :: [City] -> [City]
obtenerParesDeCiudades citiesList = primerosDos citiesList

obtieneLinks :: [City] -> [Link] -> [Link]
obtieneLinks (x:xs) linkList 
        | length((x:xs)) > 1 = [filtraLink linkList (head(primerosDos (x:xs))) (head(tail(primerosDos (x:xs))))] ++ obtieneLinks xs linkList
        | otherwise = []
                                          

tunelR :: Region -> [City] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cities links tunels) citiesList = (Reg cities links (tunels ++ [(newT (obtieneLinks citiesList links))]) )


connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg cities links tunels) city1 city2 = foldr (||) False (map (connectsT city1 city2) tunels)


linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg cities links tunels) city1 city2 = foldr (||) False (map (linksL city1 city2) links)

getConnectedTunels :: City -> City -> [Tunel] -> [Tunel]
getConnectedTunels city1 city2 tunels = foldr accumulate [] tunels
    where accumulate tunel acc = if connectsT city1 city2 tunel then tunel : acc else acc

getTunelInsideList :: [Tunel] -> Tunel
getTunelInsideList [tunel] = tunel


delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg cities links tunels) city1 city2 = if (foldr (||) False (map (connectsT city1 city2) tunels)) then  delayT(getTunelInsideList(getConnectedTunels city1 city2 tunels)) else error "Las ciudades no estan conectadas"

capacidadUtilizada :: [Tunel] -> Link -> Int
capacidadUtilizada tunelsList link = sum (map (\tunnel -> if usesT link tunnel then 1 else 0) tunelsList)  

whichLinkConnects :: City -> City -> [Link] -> Link
whichLinkConnects city1 city2 linksList = head [link | link <- linksList, linksL city1 city2 link]



availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR (Reg cities links tunels) city1 city2 = if (foldr (||) False (map (linksL city1 city2) links)) && ((capacityL (whichLinkConnects city1 city2 links) ) - (capacidadUtilizada tunels (whichLinkConnects city1 city2 links)) > 0)
    then (capacityL (whichLinkConnects city1 city2 links) ) - (capacidadUtilizada tunels (whichLinkConnects city1 city2 links)) 
    else error "Las ciudades no estan enlazadas (por un link), o no hay mas capacidad"
