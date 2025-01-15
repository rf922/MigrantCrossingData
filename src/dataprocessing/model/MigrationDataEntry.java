/*
 * File : MigrationDataEntry.java
 * 
 * 
 */
package dataprocessing.model;

import dataprocessing.MigrationDataUtils;
import java.time.LocalDate;

/**
 *
 * @author rf922b
 */
public class MigrationDataEntry implements Comparable<MigrationDataEntry> {
    private Long id;
    private LocalDate date;
    private String region;
    private String migrationPath;
    private String causeOfDeath;
    private Integer dead;
    private Integer missing;
    private Integer women;
    private Integer men;
    private Integer minors;
    private Integer survivors;
    private String regionIncident;
    private String incidentCountry;
    private String regionOrigin;
    private String countryOrigin;
    private String holder;
    private String informationSource;
    private String location;
    private String urls;
    private String detailUrl;
    private Double latitude;
    private Double longitude;

    private MigrationDataEntry(MigrationDataEntryBuilder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.region = builder.region;
        this.migrationPath = builder.migrationPath;
        this.causeOfDeath = builder.causeOfDeath;
        this.dead = builder.dead;
        this.missing = builder.missing;
        this.women = builder.women;
        this.men = builder.men;
        this.minors = builder.minors;
        this.survivors = builder.survivors;
        this.regionIncident = builder.regionIncident;
        this.incidentCountry = builder.incidentCountry;
        this.regionOrigin = builder.regionOrigin;
        this.countryOrigin = builder.countryOrigin;
        this.holder = builder.holder;
        this.informationSource = builder.informationSource;
        this.location = builder.location;
        this.urls = builder.urls;
        this.detailUrl = builder.detailUrl;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }


    public static class MigrationDataEntryBuilder {

        private Long id;
        private LocalDate date;
        private String region;
        private String migrationPath;
        private String causeOfDeath;
        private Integer dead;
        private Integer missing;
        private Integer women;
        private Integer men;
        private Integer minors;
        private Integer survivors;
        private String regionIncident;
        private String incidentCountry;
        private String regionOrigin;
        private String countryOrigin;
        private String holder;
        private String informationSource;
        private String location;
        private String urls;
        private String detailUrl;
        private Double latitude;
        private Double longitude;

        public MigrationDataEntryBuilder() {
        }

        public MigrationDataEntryBuilder id(String id) {
            this.id = MigrationDataUtils.parseLong(id);
            return this;
        }

        public MigrationDataEntryBuilder date(String date) {
            this.date = MigrationDataUtils.parseDate(date);
            return this;
        }

        public MigrationDataEntryBuilder region(String region) {
            this.region = region;
            return this;
        }

        public MigrationDataEntryBuilder migrationPath(String migrationPath) {
            this.migrationPath = migrationPath;
            return this;
        }

        public MigrationDataEntryBuilder causeOfDeath(String causeOfDeath) {
            this.causeOfDeath = causeOfDeath;
            return this;
        }

        public MigrationDataEntryBuilder dead(String dead) {
            this.dead = MigrationDataUtils.parseInteger(dead);
            return this;
        }

        public MigrationDataEntryBuilder missing(String missing) {
            this.missing = MigrationDataUtils.parseInteger(missing);
            return this;
        }

        public MigrationDataEntryBuilder women(String women) {
            this.women = MigrationDataUtils.parseInteger(women);
            return this;
        }

        public MigrationDataEntryBuilder men(String men) {
            this.men = MigrationDataUtils.parseInteger(men);
            return this;
        }

        public MigrationDataEntryBuilder minors(String minors) {
            this.minors = MigrationDataUtils.parseInteger(minors);
            return this;
        }

        public MigrationDataEntryBuilder survivors(String survivors) {
            this.survivors = MigrationDataUtils.parseInteger(survivors);
            return this;
        }

        public MigrationDataEntryBuilder regionIncident(String regionIncident) {
            this.regionIncident = regionIncident;
            return this;
        }

        public MigrationDataEntryBuilder incidentCountry(String incidentCountry) {
            this.incidentCountry = incidentCountry;
            return this;
        }

        public MigrationDataEntryBuilder regionOrigin(String regionOrigin) {
            this.regionOrigin = regionOrigin;
            return this;
        }

        public MigrationDataEntryBuilder countryOrigin(String countryOrigin) {
            this.countryOrigin = countryOrigin;
            return this;
        }

        public MigrationDataEntryBuilder holder(String holder) {
            this.holder = holder;
            return this;
        }

        public MigrationDataEntryBuilder informationSource(String informationSource) {
            this.informationSource = informationSource;
            return this;
        }

        public MigrationDataEntryBuilder location(String location) {
            this.location = location;
            return this;
        }

        public MigrationDataEntryBuilder urls(String urls) {
            this.urls = urls;
            return this;
        }

        public MigrationDataEntryBuilder detailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
            return this;
        }

        public MigrationDataEntryBuilder latitude(String latitude) {
            this.latitude = MigrationDataUtils.parseDouble(latitude);
            return this;
        }

        public MigrationDataEntryBuilder longitude(String longitude) {
            this.longitude = MigrationDataUtils.parseDouble(longitude);
            return this;
        }

        public MigrationDataEntry build() {
            if (this.id == null) {
                throw new IllegalStateException("[id] is a required field and cannot be null.");
            }

            return new MigrationDataEntry(this);
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMigrationPath() {
        return migrationPath;
    }

    public void setMigrationPath(String migrationPath) {
        this.migrationPath = migrationPath;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public Integer getDead() {
        return dead;
    }

    public void setDead(Integer dead) {
        this.dead = dead;
    }

    public Integer getMissing() {
        return missing;
    }

    public void setMissing(Integer missing) {
        this.missing = missing;
    }

    public Integer getWomen() {
        return women;
    }

    public void setWomen(Integer women) {
        this.women = women;
    }

    public Integer getMen() {
        return men;
    }

    public void setMen(Integer men) {
        this.men = men;
    }

    public Integer getMinors() {
        return minors;
    }

    public void setMinors(Integer minors) {
        this.minors = minors;
    }

    public Integer getSurvivors() {
        return survivors;
    }

    public void setSurvivors(Integer survivors) {
        this.survivors = survivors;
    }

    public String getRegionIncident() {
        return regionIncident;
    }

    public void setRegionIncident(String regionIncident) {
        this.regionIncident = regionIncident;
    }

    public String getIncidentCountry() {
        return incidentCountry;
    }

    public void setIncidentCountry(String incidentCountry) {
        this.incidentCountry = incidentCountry;
    }

    public String getRegionOrigin() {
        return regionOrigin;
    }

    public void setRegionOrigin(String regionOrigin) {
        this.regionOrigin = regionOrigin;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getInformationSource() {
        return informationSource;
    }

    public void setInformationSource(String informationSource) {
        this.informationSource = informationSource;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    @Override
    public int compareTo(MigrationDataEntry o) {
        return Long.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MigrationDataEntry{");
        sb.append("id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", region=").append(region);
        sb.append(", migrationPath=").append(migrationPath);
        sb.append(", causeOfDeath=").append(causeOfDeath);
        sb.append(", dead=").append(dead);
        sb.append(", missing=").append(missing);
        sb.append(", women=").append(women);
        sb.append(", men=").append(men);
        sb.append(", minors=").append(minors);
        sb.append(", survivors=").append(survivors);
        sb.append(", regionIncident=").append(regionIncident);
        sb.append(", incidentCountry=").append(incidentCountry);
        sb.append(", regionOrigin=").append(regionOrigin);
        sb.append(", countryOrigin=").append(countryOrigin);
        sb.append(", holder=").append(holder);
        sb.append(", informationSource=").append(informationSource);
        sb.append(", location=").append(location);
        sb.append(", urls=").append(urls);
        sb.append(", detailUrl=").append(detailUrl);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }
        
}
