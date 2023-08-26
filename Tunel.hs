module Tunel ( Tunel, newT, connectsT, usesT, delayT)
   where

import Point
import City
import Quality
import Link

data Tunel = Tun [Link] deriving (Eq, Show)  

usedCapacity :: Link -> [Link] -> Int
usedCapacity searchedLink linksList = length (filter (== searchedLink) linksList)

availableCapacity :: [Link] -> [Int]
availableCapacity linksList = map (\link -> capacityL link - usedCapacity link linksList) linksList

lessThanZero :: [Int] -> Bool
lessThanZero intsList 
   |length([lessThanZero| lessThanZero <- intsList,lessThanZero < 0]) >= 1 = True
   |otherwise = False

newT :: [Link] -> Tunel
newT linksList 
   | lessThanZero(availableCapacity linksList) = error "Algun tunel ingresado excedio su capacidad"
   | otherwise = Tun linksList

linksThatConnect :: City -> [Link] -> [Link]
linksThatConnect city linksList = [link | link <- linksList, connectsL city link]

isItExtreme :: [Link] -> City -> Bool
isItExtreme linksList city 
   |(length(linksThatConnect city linksList) > 1) || (length(linksThatConnect city linksList) == 0)  = False
   |otherwise = True

connectsT :: City -> City -> Tunel -> Bool 
connectsT city1 city2 (Tun linksList) =  (isItExtreme linksList city1) && (isItExtreme linksList city2)

usesT :: Link -> Tunel -> Bool 
usesT linkSearched (Tun linksList) = elem linkSearched linksList

delaySum :: [Float] -> Float
delaySum [] = 0
delaySum (x:xs) = x + delaySum(xs)

delayT :: Tunel -> Float
delayT (Tun linksList) = delaySum ([delayL(x) | x <- linksList])

