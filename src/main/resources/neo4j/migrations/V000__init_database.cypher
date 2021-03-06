CREATE (Abraham:Person {firstname:'Abraham', lastname: 'Simpson', birthdate: date(), gender: 'MALE'})
CREATE (Mona:Person {firstname:'Mona', lastname: 'Simpson', birthdate: date(), gender: 'FEMALE'})
CREATE (Clancy:Person {firstname:'Clancy', lastname: 'Simpson', birthdate: date(), gender: 'MALE'})
CREATE (Jacqueline:Person {firstname:'Jacqueline', lastname: 'Simpson', birthdate: date(), gender: 'FEMALE'})
CREATE (Herber:Person {firstname:'Herber', lastname: 'Simpson', birthdate: date(), gender: 'MALE'})
CREATE (Homer:Person {firstname:'Homer', lastname: 'Simpson', birthdate: date(), gender: 'MALE'})
CREATE (Marge:Person {firstname:'Marge', lastname: 'Simpson', birthdate: date(), gender: 'FEMALE'})
CREATE (Patty:Person {firstname:'Patty', lastname: 'Simpson', birthdate: date(), gender: 'FEMALE'})
CREATE (Selma:Person {firstname:'Selma', lastname: 'Simpson', birthdate: date(), gender: 'FEMALE'})
CREATE (Bart:Person {firstname: 'Bart', lastname: 'Simpson', birthdate: date(), gender: 'MALE'})
CREATE (Lisa:Person {firstname:'Lisa', lastname: 'Simpson', birthdate: date(), gender: 'FEMALE'})
CREATE (Maggie:Person {firstname: 'Maggie', lastname: 'Simpson', birthdate: date(), gender: 'FEMALE'})
CREATE (Ling:Person {firstname: 'Ling', lastname: 'Simpson', birthdate: date(), gender: 'FEMALE'})
;

MATCH (f:Person {firstname:"Homer"})
MATCH (m:Person {firstname:"Marge"})
MATCH (c:Person)
WHERE (c.firstname in ['Bart', 'Lisa', 'Maggie'])
MERGE (c)-[:HAS_FATHER]->(f)
MERGE (c)-[:HAS_MOTHER]->(m)
MERGE (f)-[:SPOUSE_OF]-(m)
;

MATCH (f:Person {firstname:"Abraham"})
MATCH(m:Person {firstname:"Mona"})
MATCH (c:Person)
WHERE c.firstname in ['Homer', 'Herber']
MERGE (c)-[:HAS_FATHER]->(f)
MERGE (c)-[:HAS_MOTHER]->(m)
MERGE (f)-[:SPOUSE_OF]-(m)
;

MATCH (f:Person {firstname:"Clancy"})
MATCH (m:Person {firstname:"Jacqueline"})
MATCH (c:Person)
WHERE c.firstname in ['Marge', 'Patty', 'Selma']
MERGE (c)-[:HAS_FATHER]->(f)
MERGE (c)-[:HAS_MOTHER]->(m)
MERGE (f)-[:SPOUSE_OF]-(m)
;

MATCH (c:Person {firstname:"Ling"})
MATCH (m:Person {firstname:"Selma"})
MERGE (c)-[:HAS_MOTHER]->(m)