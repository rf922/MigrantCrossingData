package dataprocessing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * record to represent a migration data entry object
 *
 * @author rf922
 */
public record MigrationData(
    String id,
    LocalDate fecha,
    String region,
    String rutaMigratoria,
    String causaDeLaMuerte,
    Integer muertos,
    Integer desaparecidos,
    Integer mujeres,
    Integer hombres,
    Integer menores,
    Integer supervivientes,
    String regionIncidente,
    String paisIncidente,
    String regionOrigen,
    String paisOrigen,
    String titular,
    String fuenteDeInformacion,
    String localizacion,
    String urls,
    String urlDetalle,
    Double latitud,
    Double longitud) implements Comparable<MigrationData> {

    private static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            return LocalDate.MIN; // or a default date
        }
    }

    private static Integer parseInteger(String intString) {
        try {
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            return -1; // or a default value
        }
    }

    private static Double parseDouble(String doubleString) {
        try {
            return Double.parseDouble(doubleString);
        } catch (NumberFormatException e) {
            return -1.0; // or a default value
        }
    }

    @Override
    public int compareTo(MigrationData other) {
        return this.fecha.compareTo(other.fecha);
    }

    public static MigrationData parseLine(String line) {
        String[] entries = splitLine(line);

        String id = entries[0];
        LocalDate fecha = parseDate(entries[2]);
        String region = entries[3];
        String rutaMigratoria = entries[4];
        String causaDeLaMuerte = entries[5];
        Integer muertos = parseInteger(entries[6]);
        Integer desaparecidos = parseInteger(entries[7]);
        Integer mujeres = parseInteger(entries[8]);
        Integer hombres = parseInteger(entries[9]);
        Integer menores = parseInteger(entries[10]);
        Integer supervivientes = parseInteger(entries[11]);
        String regionIncidente = entries[12];
        String paisIncidente = entries[13];
        String regionOrigen = entries[14];
        String paisOrigen = entries[15];
        String titular = entries[16];
        String fuenteDeInformacion = entries[17];
        String localizacion = entries[18];
        String urls = entries[19];
        String urlDetalle = entries[20];
        Double latitud = parseDouble(entries[21]);
        Double longitud = parseDouble(entries[22]);
        return new MigrationData(
            id, fecha, region, rutaMigratoria, causaDeLaMuerte,
            muertos, desaparecidos, mujeres, hombres, menores, supervivientes,
            regionIncidente, paisIncidente, regionOrigen, paisOrigen,
            titular, fuenteDeInformacion, localizacion, urls, urlDetalle,
            latitud, longitud
        );
    }

    private static String[] splitLine(String line) {
        List<String> fields = new ArrayList<>();
        Matcher matcher = Pattern.compile("(?<=^|;)(\"(?:[^\"]|\"\")*\"|[^;]*)").matcher(line);
        while (matcher.find()) {
            fields.add(matcher.group(1).replaceAll("^\"|\"$", "").replace("\"\"", "\""));
        }
        return fields.toArray(String[]::new);

    }
}
