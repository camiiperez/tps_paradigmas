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

linkThatConnects :: [Link] -> City -> City -> Link
linkThatConnects linksList city1 city2 = head [link | link <- linksList, linksL city1 city2 link]

firstTwo :: [a] -> [a]
firstTwo (x:y:_) = [x, y]
firstTwo _ = []  

obtenerParesDeCiudades :: [City] -> [City]
obtenerParesDeCiudades citiesList = firstTwo citiesList

firstCity :: [City] -> City
firstCity citiesList = (head (firstTwo citiesList))

secondCity :: [City] -> City
secondCity citiesList = (head(tail(firstTwo citiesList)))

obtieneLinks :: [City] -> [Link] -> [Link]
obtieneLinks (x:xs) linkList 
        | length((x:xs)) > 1 = [linkThatConnects linkList (firstCity (x:xs)) (secondCity(x:xs))] ++ obtieneLinks xs linkList
        | otherwise = []
                                          

tunelR :: Region -> [City] -> Region 
tunelR (Reg cities links tunels) citiesList 
        | (length(citiesList) > 2) =  (Reg cities links (tunels ++ [(newT (obtieneLinks citiesList links))]) )
        | (length(citiesList) == 2) = (Reg cities links (tunels ++ [(newT [linkThatConnects links (head(citiesList)) (head(tail(citiesList)))])]))
        | otherwise = error "No se puede crear un tunel con una sola ciudad"

connectedR :: Region -> City -> City -> Bool 
connectedR (Reg cities links tunels) city1 city2 
        | (nameC(city1) /= nameC(city2)) = foldr (||) False (map (connectsT city1 city2) tunels)
        | otherwise = error "Ingresaste la misma ciudad"

linkedR :: Region -> City -> City -> Bool 
linkedR (Reg cities links tunels) city1 city2 = foldr (||) False (map (linksL city1 city2) links)

getConnectedTunels :: City -> City -> [Tunel] -> [Tunel]
getConnectedTunels city1 city2 tunels = foldr accumulate [] tunels
    where accumulate tunel acc = if connectsT city1 city2 tunel then tunel : acc else acc

areConnectedByT :: City -> City -> [Tunel] -> Bool
areConnectedByT city1 city2 tunelsList = (foldr (||) False (map(connectsT city1 city2) tunelsList))

delayR :: Region -> City -> City -> Float 
delayR (Reg cities links tunels) city1 city2 
    | areConnectedByT city1 city2 tunels = delayT(head(getConnectedTunels city1 city2 tunels))
    | otherwise = error "Las ciudades no estan conectadas"

usedCapacityR :: [Tunel] -> Link -> Int
usedCapacityR tunelsList link = sum (map (\tunnel -> if usesT link tunnel then 1 else 0) tunelsList)  

whichLinkConnects :: City -> City -> [Link] -> Link
whichLinkConnects city1 city2 linksList = head [link | link <- linksList, linksL city1 city2 link]

availableCapacityForLink :: City -> City -> [Link] -> [Tunel] -> Int
availableCapacityForLink city1 city2 linksList tunels = 
        (capacityL (whichLinkConnects city1 city2 linksList) ) - (usedCapacityR tunels (whichLinkConnects city1 city2 linksList))

areCitiesLinked :: City -> City -> [Link] -> Bool
areCitiesLinked city1 city2 linksList = (foldr (||) False (map (linksL city1 city2) linksList))

existsLwCapacity :: City -> City -> [Link] -> [Tunel] -> Bool
existsLwCapacity city1 city2 links tunels = 
        areCitiesLinked city1 city2 links && ((availableCapacityForLink city1 city2 links tunels) > 0)


availableCapacityForR :: Region -> City -> City -> Int 
availableCapacityForR (Reg cities links tunels) city1 city2 
<<<<<<< HEAD
        | existsLwCapacity city1 city2 links tunels = (availableCapacityForLink city1 city2 links tunels) 
        | otherwise = error "Las ciudades no estan enlazadas (por un link), o no hay mas capacidad"
        
=======
        | existsLwCapacity city1 city2 links tunels = (capacityL (whichLinkConnects city1 city2 links) ) - (usedCapacityR tunels (whichLinkConnects city1 city2 links)) 
        | otherwise = error "Las ciudades no estan enlazadas (por un link), o no hay mas capacidad"
>>>>>>> 44482fe42687dabfabcb7c08463b75fea663fcdc
