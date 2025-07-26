db = db.getSiblingDB('acal');

db.createCollection('category');
db.category.createIndex(
    { name: 1, group: 1 },
    {
        name: 'unique_category_index',
        unique: true
    }
);


db.createCollection('address');
db.address.createIndex({ name: 1 }, { unique: true });

db.createCollection("place");

db.place.createIndex(
  { name: 1, letter: 1, number: 1 },
  {
    name: 'unique_place_index',
    unique: true
  }
);