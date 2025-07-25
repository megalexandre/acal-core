db = db.getSiblingDB('acal');

db.createCollection('category');
db.category.createIndex({ name: 1 }, { unique: true });
db.createCollection("address");
